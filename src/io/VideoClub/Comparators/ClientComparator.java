/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Comparators;

import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Enums.SortOptions;
import java.util.Comparator;

/**
 *
 * @author migue
 */
public class ClientComparator implements Comparator<IClient> {
    
    private SortOptions option;

    public SortOptions getOption() {
        return option;
    }

    public void setOption(SortOptions option) {
        this.option = option;
    }
    @Override
    public int compare(IClient o1, IClient o2) {
        SortOptions opcion = option;
         int result = 0;
        switch(opcion){
            case AToZC:
                result = o1.getName().compareTo(o2.getName());
                break;
            case ZtoAC:
                result = o2.getName().compareTo(o1.getName());
                break;
            case Phone:
                result = o1.getPhone().compareTo(o2.getPhone());
                break;
            case IDMinToMax:
                result = o1.getID().compareTo(o2.getID());
                break;
            case IDMaxToMin:
                result = o2.getID().compareTo(o1.getID());
                break;
            default:
                break;
                
        }
        return result;
    }

    

    
    
}
