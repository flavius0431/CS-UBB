package com.example.practic.service;

import com.example.practic.domain.City;
import com.example.practic.domain.TrainStation;
import com.example.practic.repository.AbstractDBRepository;
import com.example.practic.utils.Observable;
import com.example.practic.utils.Observer;
import com.example.practic.utils.events.EntityChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service implements Observable<EntityChangeEvent> {



    private static final float PRICE_PER_STATIONS = 10;
    private AbstractDBRepository<Long, City> repoC ;
    private AbstractDBRepository<Long, TrainStation> repoTC ;
    private List<Observer<EntityChangeEvent>> observers = new ArrayList<>();

    public Service(AbstractDBRepository<Long, City> repoC, AbstractDBRepository<Long, TrainStation> repoTC) {
        this.repoC = repoC;
        this.repoTC = repoTC;
    }

    public Iterable<City> getCities(){
        return this.repoC.findAll();
    }

    public Iterable<TrainStation> getTraiStation(){
        return this.repoTC.findAll();
    }
    public Iterable<String> getRoutes(City Depature, City Destination, Boolean chech){
        List<String> route = new ArrayList<>();
        String ruta = "";
        //List<TrainStation> routetrain= new ArrayList<>();
        for(TrainStation trainStation: getTraiStation()){
            if(GetTrainStationId(Depature,Destination)!=null && chech){
                Integer NUMBER_OF_STATIONS=2;
                ruta += Depature.getName() +" " + trainStation.getId()+ " " + Destination.getName()+" "+PRICE_PER_STATIONS*NUMBER_OF_STATIONS+ "\n";
                System.out.println(ruta);
                break;

            }else if(Objects.equals(GetDepatureId(Depature), trainStation.getId()) && !chech){
            }
        }
        route.add(ruta);
        System.out.println(route);
        return route;
    }


    public Long GetTrainStationId(City Depature, City Destination) {
       for(TrainStation trainStation: getTraiStation()){
           if(Objects.equals(trainStation.getDepartureCityId(), Depature.getId()) && Objects.equals(trainStation.getDestinationCityId(), Destination.getId())){
               return trainStation.getId();
           }
       }
       return null;
    }

    public Long GetDepatureId(City Depature) {
        for(TrainStation trainStation: getTraiStation()){
            if(Objects.equals(trainStation.getDepartureCityId(), Depature.getId())){
                    return trainStation.getId();
            }
        }
        return null;
    }

    public Long GetDestinationId( City Destination) {
        for(TrainStation trainStation: getTraiStation()){
            if( trainStation.getDestinationCityId()==Destination.getId()){
                return trainStation.getId();
            }
        }
        return null;
    }




    public TrainStation getTrainStationByID(Long id){
        for(TrainStation trainStation:getTraiStation()){
            if (Objects.equals(trainStation.getId(), id)){
                return trainStation;
            }
        }
        return null;
    }



   //OBSERVER FUNCTIONS
    //notifyObservers(new EntityChangeEvent(ChangeEventType.ADD,m)); la functii
    @Override
    public void addObserver(Observer<EntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<EntityChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(EntityChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }




}
