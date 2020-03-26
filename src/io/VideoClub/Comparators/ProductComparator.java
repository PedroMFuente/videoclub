/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Comparators;

import io.VideoClub.Model.Product;
import io.VideoClub.Model.Enums.SortOptions;
import java.util.Comparator;
/**
 *
 * @author migue
 */
public class ProductComparator implements Comparator<Product>  {

    private SortOptions option;

    public SortOptions getOption() {
        return option;
    }

    public void setOption(SortOptions option) {
        this.option = option;
    }
    
    
    @Override
    public int compare(Product o1, Product o2) {
         SortOptions opcion = option;
         int result = 0;
        switch(opcion){
            case AToZP:
                result = o1.getName().compareTo(o2.getName());
                break;
            case ZtoAP:
                result = o2.getName().compareTo(o1.getName());
                break;
            default:
                break;
                
        }
        return result;
    }
    
    
}
