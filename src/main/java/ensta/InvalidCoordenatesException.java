package ensta;
public class InvalidCoordenatesException extends Exception{
    public  InvalidCoordenatesException(){
        super("Invalid coordenates, Please choose positions that includes your ship entirely inside the grid and not on top of other ships !");
    }

        }