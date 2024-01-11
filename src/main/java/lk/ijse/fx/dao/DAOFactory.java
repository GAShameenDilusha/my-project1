package lk.ijse.fx.dao;


import lk.ijse.fx.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private  DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        ATTENDENCE,CHILDREN,CHURCH,EVENT,FATHER,LOGIN,PAYMENT,REGISTRATION,SIGNUP,VEHICLE,VISIT
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes) {
            case ATTENDENCE:
                return new AttendenceDAOImpl();
            case CHILDREN:
                return new ChildrenDAOImpl();
            case CHURCH:
                return  new ChurchDAOImpl();
            case EVENT:
                return new EventDAOImpl();
            case FATHER:
                return new FatherDAOImpl();
            case LOGIN:
                return (SuperDAO) new LoginDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case SIGNUP:
                return (SuperDAO) new SignupDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case VISIT:
                return new VisitDAOImpl();
            default:
                return null;
        }

    }
}
