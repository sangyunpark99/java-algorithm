import java.util.*;

class Solution {
    
    // 모든 차량이 한 번은 단속 카메라를 만나도록 할려면 최소 몇대의 카메라를 설치하는가?
    // 최소 카메라를 설치하기 위해선 각 차량이 최대한 많이 겹치는 부분에 카메라를 설치한다.
    // 차량 범위가 겹치는 부분은 어떻게 찾는가?
    // 도착 지점 기준 정렬?
    // [-20,-15], [-18,-13], [-14,-5], [-5,-3]
    // 도착 지점이 같은 경우엔? 출발 지점을 기준으로 정렬
    public int solution(int[][] routes) {
        
        List<CarRoute> carRoutes = new ArrayList<>();
        
        for(int[] route: routes) {
            carRoutes.add(new CarRoute(route[0], route[1]));
        }
        
        Collections.sort(carRoutes,(o1, o2) -> {
            if(o1.end == o2.end) {
                return o1.start - o2.start;
            }
            
            return o1.end - o2.end;
        });
        
        int cameraCnt = 0;
        int curCameraPosition = -30001;
        
        for(CarRoute carRoute : carRoutes) {
            if(carRoute.start > curCameraPosition) {
                cameraCnt++;
                curCameraPosition = carRoute.end;
            }
        }
        
        return cameraCnt;
    }
    
    public class CarRoute {
        int start;
        int end;
        
        public CarRoute(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}