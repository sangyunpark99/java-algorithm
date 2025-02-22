import java.util.*;

class Solution {

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