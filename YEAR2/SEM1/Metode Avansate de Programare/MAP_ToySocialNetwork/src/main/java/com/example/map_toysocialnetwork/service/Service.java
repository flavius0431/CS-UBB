package com.example.map_toysocialnetwork.service;

import com.example.map_toysocialnetwork.controller.HomePageController;
import com.example.map_toysocialnetwork.domain.Message;
import com.example.map_toysocialnetwork.domain.Prietenie;
import com.example.map_toysocialnetwork.domain.Utilizator;
import com.example.map_toysocialnetwork.domain.UtilizatorPrietenieDTO;
import com.example.map_toysocialnetwork.domain.validators.MessageValidator;
import com.example.map_toysocialnetwork.domain.validators.ValidationException;
import com.example.map_toysocialnetwork.repository.Repository0;
import com.example.map_toysocialnetwork.utils.events.ChangeEventType;
import com.example.map_toysocialnetwork.utils.events.FriendshipEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.events.UserEntityChangeEvent;
import com.example.map_toysocialnetwork.utils.observer.Observable;
import com.example.map_toysocialnetwork.utils.observer.Observer;


import java.time.LocalDateTime;
import java.util.*;

//era Service<ID>
//in caz de eroare
public class Service implements Observable<FriendshipEntityChangeEvent> {
    private Repository0<Long, Utilizator> Repo;
    private Repository0<Long, Prietenie> pRepo;

    private Repository0<Long, Message> mRepo;

    private List<Observer<FriendshipEntityChangeEvent>> observers = new ArrayList<>();

    public Service(Repository0<Long, Utilizator> repo, Repository0<Long, Prietenie> pRepo) {
        Repo = repo;
        this.pRepo = pRepo;
    }

    public Service(Repository0<Long, Utilizator> repo, Repository0<Long, Prietenie> pRepo, Repository0<Long, Message> mRepo) {
        Repo = repo;
        this.pRepo = pRepo;
        this.mRepo = mRepo;
    }

    //pt repo in memorie fara clasa de prietenie
    public Service(Repository0<Long, Utilizator> repo) {
        Repo = repo;
    }

    /*public void addUser(Long ID, String fname,String lname,String email, String password)
    {
        if( Repo.findOne(ID)!=null){
            throw new IllegalArgumentException("exista deja acest utilizator ");
        }
        Utilizator u = new Utilizator(fname,lname,email,password);
        u.setId(ID);
        this.Repo.save(u);
    }*/

    public void saveUser(String fname, String lname, String email, String password) {
        if (findOneEmail(email) != null) {
            throw new IllegalArgumentException("exista deja acest utilizator ");
        }
        Utilizator u = new Utilizator(fname, lname, email, password);
        this.Repo.save(u);
    }

    public void saveMessage(Long senderid,Long reciverid,String content){
        Message m =new Message(senderid,reciverid,content,LocalDateTime.now());
        this.mRepo.save(m);


    }

    public void removeUser(Long ID) {
        if (Repo.findOne(ID) == null) {
            throw new IllegalArgumentException("Utilizatorul cu id " + ID + " nu exista");
        }
        Utilizator u = Repo.findOne(ID);

        for (Prietenie prietenie : this.pRepo.findAll()) {
            if (prietenie.getID1().equals(ID) || Objects.equals(prietenie.getID2(), ID)) {
                this.pRepo.delete(prietenie.getId());
            }
        }
        this.Repo.delete(ID);
    }

    public Iterable<Utilizator> users() {
        return this.Repo.findAll();
    }

    public void updateUser(Long ID, String FirstName, String LastName) {
        if (Repo.findOne(ID) == null) {
            throw new IllegalArgumentException("Utilizatorul cu id " + ID + " nu exista");
        }
        Utilizator u = this.Repo.findOne(ID);
        Utilizator newU = new Utilizator(FirstName, LastName);
        newU.setId(ID);
        ;
        this.Repo.update(newU);
    }

    public Utilizator findOneEmail(String email) {

        for (Utilizator user : Repo.findAll()) {
            if (Objects.equals(user.getEmail(), email)) {
                return user;
            }
        }
        return null;
    }

    //LOG IN
    public Utilizator LogIn(String email, String password) {
        Utilizator u = findOneEmail(email);
        if (u == null) {
            throw new IllegalArgumentException("Nu exista Utilizatorul cu email-ul introdus");
        }
        if (!Objects.equals(u.getPassword(), password)) {
            throw new IllegalArgumentException("Parola introdusa nu este corecta");
        }
        return u;
    }

    public void updatePrietenie(Long ID1, Long ID2, LocalDateTime Prietenidin, String status) {
        Long ID = Long.min(ID1, ID2) * 1000 + Long.max(ID1, ID2);
        if (pRepo.findOne(ID) == null) {
            throw new IllegalArgumentException("Prietenia cu id " + ID + " nu exista");
        }
        Prietenie p = this.pRepo.findOne(ID);
        Prietenie newP = new Prietenie(ID1, ID2, Prietenidin, status);
        newP.setId(ID);
        this.pRepo.update(newP);
        notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.UPDATE, newP));

    }

    public Iterable<Prietenie> prietenii() {
        return this.pRepo.findAll();
    }


    private boolean areFriends(Utilizator u1, Utilizator u2) {
        if (this.Repo.findOne(u1.getId()) == null || this.Repo.findOne(u2.getId()) == null) {
            throw new IllegalArgumentException("Unul dintre utilizatori nu exista");
        }
        for (Prietenie prietenie : this.pRepo.findAll()) {
            if (Objects.equals(prietenie.getID1(), u1.getId()) && (Objects.equals(prietenie.getID2(), u2.getId())) && Objects.equals(prietenie.getStatus(), "accepted")) {
                return true;
            }
            if (Objects.equals(prietenie.getID1(), u2.getId()) && (Objects.equals(prietenie.getID2(), u1.getId())) && Objects.equals(prietenie.getStatus(), "accepted")) {
                return true;
            }
        }
        return false;
    }

    public void makeFriends(Long ID1, Long ID2) {
/*
        Utilizator u1 = Repo.findOne(ID1);
        Utilizator u2 = Repo.findOne(ID2);

        if(u1==null || u2==null)
            throw new IllegalArgumentException("Unul dintre utilizatori nu exista");

        if (this.areFriends(u1, u2)) {
            throw new ValidationException(u2 + " este deja prieten cu " + u1);
        }
        if(Objects.equals(u1.getId(),u2.getId())){
            throw new ValidationException("Nu te poti adauga pe tine ca prieten");
        }*/

        Prietenie p = new Prietenie(ID1, ID2, LocalDateTime.now(), "pending");
        Long ID = Long.min(ID1, ID2) * 1000 + Long.max(ID1, ID2);
        p.setId(ID);
        this.pRepo.save(p);
        notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.ADD, p));
    }

    public Prietenie findFriendship(Long ID1, Long ID2) {
        Long ID = Long.min(ID1, ID2) * 1000 + Long.max(ID1, ID2);
        return pRepo.findOne(ID);

    }

    public void removeFriends(Long ID1, Long ID2) {

        Utilizator u1 = Repo.findOne(ID1);
        Utilizator u2 = Repo.findOne(ID2);
        if (u1 == null || u2 == null)
            throw new IllegalArgumentException("Unul dintre utilizatori nu exista");

        /*if(!this.areFriends(u1,u2)){
            throw new ValidationException(u1 +" si "+ u2 + "nu sunt prieteni");
        }*/
        Long ID = Long.min(ID1, ID2) * 1000 + Long.max(ID1, ID2);

        this.pRepo.delete(ID);
        notifyObservers(new FriendshipEntityChangeEvent(ChangeEventType.DELETE, findFriendship(ID1, ID2)));
    }

    public List<Utilizator> friendsList(Long id) {

        List<Utilizator> friends = new ArrayList<>();
        for (Prietenie prietenie : this.pRepo.findAll()) {
            if (Objects.equals(prietenie.getID1(), id) && Objects.equals(prietenie.getStatus(), "accepted")) {
                friends.add(this.Repo.findOne(prietenie.getID2()));
            }
            if (Objects.equals(prietenie.getID2(), id) && Objects.equals(prietenie.getStatus(), "accepted")) {
                friends.add(this.Repo.findOne(prietenie.getID1()));
            }
        }
        return friends;
    }

    private void dfs(Utilizator utilizator, Map<Long, Boolean> visited, List<Utilizator> comp) {
        visited.put(utilizator.getId(), true);
        comp.add(utilizator);
        for (Utilizator friend : friendsList(utilizator.getId())) {
            if (visited.get(friend.getId()) == null) {
                dfs(friend, visited, comp);
            }
        }
    }

    public int getComunitati(List<List<Utilizator>> listComunitati) {
        int comunitati = 0;
        Map<Long, Boolean> visited = new HashMap<Long, Boolean>();
        for (Utilizator utilizator : this.Repo.findAll()) {
            if (visited.get(utilizator.getId()) == null) {
                List<Utilizator> auxList = new ArrayList<Utilizator>();
                dfs(utilizator, visited, auxList);
                listComunitati.add(auxList);
                ++comunitati;
            }
        }
        return comunitati;
    }

    private int bfs(List<Utilizator> users, Utilizator start) {
        Map<Long, Integer> distances = new HashMap<>();

        distances.put(start.getId(), 0);
        Queue<Utilizator> utilizatorQueue = new LinkedList<>();
        utilizatorQueue.add(start);

        while (!utilizatorQueue.isEmpty()) {
            Utilizator utilizator = utilizatorQueue.remove();
            Integer dist = distances.get(utilizator.getId());
            for (Utilizator friend : friendsList(utilizator.getId())) {
                if (distances.get(friend.getId()) == null) {
                    distances.put(friend.getId(), 1 + dist);
                    utilizatorQueue.add(friend);
                } else if (distances.get(friend.getId()) > 1 + dist) {
                    distances.replace(friend.getId(), 1 + dist);
                    utilizatorQueue.add(friend);
                }
            }
        }

        int ans = 0;
        for (Map.Entry<Long, Integer> entries : distances.entrySet()) {
            ans = Integer.max(ans, entries.getValue());
        }

        return ans;
    }

    public List<Utilizator> getComunitateSociabila(List<List<Utilizator>> listComunitati) {
        int ansVal = 0;
        List<Utilizator> comunitateSociabila = new ArrayList<>();
        for (List<Utilizator> componenta : listComunitati) {
            int ansInt = 0;
            for (Utilizator utilizator : componenta) {
                ansInt = Integer.max(ansInt, bfs(componenta, utilizator));
            }

            if (ansInt > ansVal) {
                ansVal = ansInt;
                comunitateSociabila = componenta;
            }
        }
        return comunitateSociabila;
    }

    /////////////OBSERVER
    @Override
    public void addObserver(Observer<FriendshipEntityChangeEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<FriendshipEntityChangeEvent> e) {
        observers.remove(e);

    }

    @Override
    public void notifyObservers(FriendshipEntityChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));

    }
////////////

    public List<UtilizatorPrietenieDTO> getFriendshipsRequest(Long uID) {
        List<UtilizatorPrietenieDTO> friendsrequest = new ArrayList<>();
        for (Prietenie prietenie : this.pRepo.findAll()) {
            if (Objects.equals(prietenie.getID2(), uID) && (Objects.equals(prietenie.getStatus(), "accepted") || Objects.equals(prietenie.getStatus(), "pending"))) {
                Utilizator userFriend = this.Repo.findOne(prietenie.getID1());
                UtilizatorPrietenieDTO request = new UtilizatorPrietenieDTO(userFriend.getId(), userFriend.getFirstName(), userFriend.getLastName(), prietenie.getPrietenidin(), prietenie.getStatus());
                friendsrequest.add(request);
            }
        }
        return friendsrequest;
    }

    public List<Utilizator> getNotFriend(Long idUser) {
        List<Utilizator> friends = friendsList(idUser);
        List<Utilizator> notFriend = new ArrayList<>();
        for (Utilizator utilizator : this.Repo.findAll()) {
            if (!friends.contains(utilizator) && !Objects.equals(utilizator.getId(), idUser)) {
                notFriend.add(utilizator);
            }
        }
        return notFriend;
    }

    public List<UtilizatorPrietenieDTO> getSentRequest(Long uID) {
        List<UtilizatorPrietenieDTO> sentrequest = new ArrayList<>();
        for (Prietenie prietenie : this.pRepo.findAll()) {
            if (Objects.equals(prietenie.getID1(), uID) && Objects.equals(prietenie.getStatus(), "pending")) {
                Utilizator userRequest = this.Repo.findOne(prietenie.getID2());
                UtilizatorPrietenieDTO request = new UtilizatorPrietenieDTO(userRequest.getId(), userRequest.getFirstName(), userRequest.getLastName(), prietenie.getPrietenidin(), prietenie.getStatus());
                sentrequest.add(request);
            }
        }
        return sentrequest;
    }


    public List<Message> getMyMessages(Long myID, Long friendID) {
        List<Message> messages = new ArrayList<>();
        for (Message m : this.mRepo.findAll()) {
            if ((Objects.equals(m.getSenderID(), myID) && (Objects.equals(m.getReceiverID(), friendID))) || (Objects.equals(m.getSenderID(), friendID) && (Objects.equals(m.getReceiverID(), myID)))) {
                messages.add(m);
            }
        }
        messages.sort(Comparator.comparing(Message::getDateSent));

        return messages;
    }



    
    
}

