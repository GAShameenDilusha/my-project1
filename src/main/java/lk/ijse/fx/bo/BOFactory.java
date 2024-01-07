package lk.ijse.fx.bo;

import lk.ijse.fx.dao.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;
    }

    public enum BOTypes{
        ATTENDENCE,CHILDREN,CHURCH,EVENT,FATHER,LOGIN,PAYMENT,REGISTRATION,SIGNUP,VEHICLE,VISIT
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ATTENDENCE:
                return new AttendenceDAOImpl();
            case CHILDREN:
                return new ChildrenDAOImpl();
            case CHURCH:
                return new ChurchDAOImpl();
            case EVENT:
                return new EventDAOImpl();
            case FATHER:
                return new FatherDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case SIGNUP:
                return new SignupDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case VISIT:
                return new VisitDAOImpl();
            default:
                return null;
        }
    }
}
