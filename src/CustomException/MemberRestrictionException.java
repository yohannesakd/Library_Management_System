/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomException;

/**
 *
 * @author JOHN
 */
public class MemberRestrictionException extends Exception {

    /**
     *
     */
    public MemberRestrictionException(){
            super("Please Input Correct Email");
    }
}
