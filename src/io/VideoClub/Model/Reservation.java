package io.VideoClub.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Reservation implements Comparable<Reservation>{

    @Override
    public int compareTo(Reservation t) {
        return t.pro.getName().compareTo(this.pro.getName());
    }
    public enum StatusReserve{
        ACTIVE,  //ini on, finished off
        FINISHED, //ini on finised on == end
        PENDING  //ini on , finished off and end past
    }
    public Product pro;
    public IClient cli;
    public LocalDate ini;
    public LocalDate end;
    public LocalDate finished;
    public StatusReserve status;

    public StatusReserve getStatus() {
        return status;
    }
    

    public Product getPro() {
        return pro;
    }

    public void setStatus(StatusReserve status) {
        this.status = status;
    }
    

    public void setPro(Product pro) {
        this.pro = pro;
    }

    public IClient getCli() {
        return cli;
    }

    public void setCli(IClient cli) {
        this.cli = cli;
    }

    public LocalDate getIni() {
        return ini;
    }

    public void setIni(LocalDate ini) {
        this.ini = ini;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalDate getFinished() {
        return finished;
    }

    public void setFinished(LocalDate finished) {
        this.finished = finished;
    }

    
    private Reservation(){};
    public Reservation(Product pro,IClient cli){
        this.pro=pro;
        this.cli=cli;
        ini=LocalDate.now();
        end=LocalDate.now().plusDays(2);
        if(end.getDayOfWeek()==DayOfWeek.SUNDAY){
            end=end.plusDays(1);
        }
        status=StatusReserve.ACTIVE;
    }
    
    public boolean equals(Object o){
        boolean result=false;
        if(o!=null){
            if(o instanceof Reservation){
                Reservation other=(Reservation)o;
                if(this.pro.equals(other.pro) 
                        &&this.cli.equals(other.cli)
                        &&this.ini.equals(other.ini)
                        &&this.end.equals(other.end)
                        &&this.status==other.status){
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" + "pro=" + pro + ", cli=" + cli + ", ini=" + ini + ", end=" + end + ", finished=" + finished + ", status=" + status + '}';
    }
    
}
