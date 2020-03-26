/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Comparators;


import io.VideoClub.Model.Reservation;
import java.util.Comparator;
import io.VideoClub.Model.Enums.SortOptions;


/**
 *
 * @author migue
 */
public class ReservationComparator implements Comparator<Reservation>{
    private SortOptions option;

    public SortOptions getOption() {
        return option;
    }

    public void setOption(SortOptions option) {
        this.option = option;
    }
    @Override
    public int compare(Reservation o1, Reservation o2) {
        SortOptions opcion = option;
        int result = 0;
        switch(opcion){
            
            //CLIENT SORT
            case AToZC:
                result = o1.cli.getName().compareTo(o2.cli.getName());
                break;
            case ZtoAC:
                result = o2.cli.getName().compareTo(o1.cli.getName());
                break;
            case Phone:
                result = o1.cli.getPhone().compareTo(o2.cli.getPhone());
                break;
            case IDMinToMax:
                result = o1.cli.getID().compareTo(o2.cli.getID());
                break;
            case IDMaxToMin:
                result = o2.cli.getID().compareTo(o1.cli.getID());
                break;
             
            //PRODUCT SORT
            case AToZP:
                result = o1.pro.getName().compareTo(o2.pro.getName());
                break;
            case ZtoAP:
                result = o2.pro.getName().compareTo(o1.pro.getName());
                break;
                
            //RESERVATION SORT
                
            default:
                break;
        
    }

    
    return result;
}
}
