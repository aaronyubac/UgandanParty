public interface Event {

    void run(Player player);
}

class Beginning implements Event {

    public void run(Player player) {
        // give points
        System.out.println("player reached beginning");
        player.score += 3;
    }
}

class Nothing implements Event {

    public void run(Player player) {
        // do nothing
        System.out.println("nothing happened");
    }
}
