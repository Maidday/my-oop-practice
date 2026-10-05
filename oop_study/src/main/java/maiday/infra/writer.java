package maiday.infra;

public class writer {
    private writer(){}

    private static final writer INSTANCE = new writer();

    public static writer get_instance(){return  INSTANCE;}
    
}
