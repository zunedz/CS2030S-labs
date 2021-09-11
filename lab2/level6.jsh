/open Cruise.java
/open Loader.java
/open SmallCruise.java
/open BigCruise.java

import java.util.ArrayList;

void serveCruises(Cruise[] cruises) {
    int maxNumOfLoadersPerCruise = 9;
    int maxNumOfCruise = 30;
    int totalNumOfLoaders = 0;
    ArrayList<Loader> loaders = new ArrayList<Loader>();
    int recycleNumber = 3;
    for (Cruise cruise: cruises) {
        int numOfLoaders = cruise.getNumOfLoadersRequired();
        int iterator = 0;
        while (numOfLoaders > 0) {
            if (iterator >= totalNumOfLoaders) {
                if ((iterator + 1) % recycleNumber == 0) {
                    loaders.add(new RecycledLoader(iterator + 1, cruise));
                } else {
                    loaders.add(new Loader(iterator + 1, cruise));
                }
                System.out.println(loaders.get(iterator));
                numOfLoaders -= 1;
                totalNumOfLoaders += 1;
            } else if (loaders.get(iterator).canServe(cruise))  {
                loaders.set(iterator, loaders.get(iterator).serve(cruise));
                System.out.println(loaders.get(iterator));
                numOfLoaders -= 1;
            }
            iterator++;
        }
    }
}

