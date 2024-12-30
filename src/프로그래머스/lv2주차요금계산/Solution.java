package 프로그래머스.lv2주차요금계산;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();

        // 주차 요금 계산
        // 출차 내역이 없는 경우 23:59분에 출차
        // 누적 주차 시간이 기본 시간 이하 -> 기본 요금 청구
        // 기본 시간 초과 -> 기본 요금 + 초과한 시간 단위 시간마다 단위 요금 청구
        // 초과한 시간 단위시간으로 나누어떨어지지 않는 경우 올림
        // 차량번호가 작은 자동차부터

        // 기본 시간, 기본 요금, 단위 시간, 단위 요금
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        // IN : HashMap으로 <차량이름, 시간>으로 넣어준다. O(1)
        HashMap<String, String> parkingLot = new HashMap<>();

        // 자동차, 최종 요금
        HashMap<String, Integer> carFees = new HashMap<>();
        HashMap<String, Integer> carTotalTime = new HashMap<>();

        for (String record : records) {
            String[] value = record.split(" ");
            String carName = value[1];
            String status = value[2];

            if ("IN".equals(status)) {
                parkingLot.put(carName, value[0]);
                continue;
            }

            // OUT : HashMap에서 꺼내서 비용을 계산한다. O(1)
            if ("OUT".equals(status)) {
                String outTime = value[0]; // 주차장 나오는 시간
                String startTime = parkingLot.get(carName); // 주차장 들어간 시간

                // 끝나는 시간 - 시작 시간
                int gapTime = calculateGapTime(startTime, outTime);
                addTotalTime(carTotalTime, gapTime, carName);

                // 주차장에서 자동차 제거
                parkingLot.remove(carName);
            }
        }

        // 주차장에 자동차가 남아 있는 경우
        if (parkingLot.size() > 0) {
            for (Map.Entry<String, String> entry : parkingLot.entrySet()) { // 남아있는 자동차 주차 시간 더해주기
                int gapTime = calculateGapTime(parkingLot.get(entry.getKey()), "23:59");
                addTotalTime(carTotalTime, gapTime, entry.getKey());
            }
        }

        // 비용 합산
        // 비용 계산
        for (Map.Entry<String, Integer> entry : carTotalTime.entrySet()) {
            int fee = calculateFee(carTotalTime.get(entry.getKey()), defaultTime, defaultFee, unitTime, unitFee);
            String carName = entry.getKey();
            if (carFees.containsKey(carName)) { // 자동차, 비용란에 등록이 되어 있는 경우
                carFees.put(carName, carFees.get(carName) + fee); // 기존값 + 비용
            } else {
                carFees.put(carName, fee); // 비용
            }
        }

        Set<String> car = carFees.keySet();
        List<String> sortedCarNames = new ArrayList<>(car);
        Collections.sort(sortedCarNames);

        for (String value : sortedCarNames) {
            answer.add(carFees.get(value));
        }

        return answer.stream().mapToInt(o -> o).toArray();
    }

    public void addTotalTime(Map<String, Integer> carTotalTime, int gapTime, String carName) {
        if (carTotalTime.containsKey(carName)) {
            carTotalTime.put(carName, carTotalTime.get(carName) + gapTime);
        } else {
            carTotalTime.put(carName, gapTime);
        }
    }

    public int calculateGapTime(String startTime, String outTime) {
        int startTimeHour = convertHourToMinute(Integer.parseInt(startTime.charAt(0) + "" + startTime.charAt(1) + ""));
        int startTimeMinute = Integer.parseInt(startTime.charAt(3) + "" + startTime.charAt(4) + "");

        // 끝나는 시간 분으로 환산
        int outTimeHour = convertHourToMinute(Integer.parseInt(outTime.charAt(0) + "" + outTime.charAt(1) + ""));
        int outTimeMinute = Integer.parseInt(outTime.charAt(3) + "" + outTime.charAt(4) + "");

        // 끝나는 시간 - 시작 시간
        int gapTime = (outTimeHour + outTimeMinute) - (startTimeHour + startTimeMinute);

        return gapTime;

    }

    public int calculateFee(int gapTime, int defaultTime, int defaultFee, int unitTime, int unitFee) {

        int fee = 0;

        if (gapTime >= defaultTime) { // 주차시간이 기본시간보다 큰 경우 기본시간 제
            gapTime -= defaultTime;
            fee += defaultFee + Math.ceil((double) gapTime / unitTime) * unitFee;
        } else { // 기본 시간 보다 작은 경우
            fee += defaultFee;
        }

        return fee;
    }

    public int convertHourToMinute(int time) {
        return time * 60;
    }
}
