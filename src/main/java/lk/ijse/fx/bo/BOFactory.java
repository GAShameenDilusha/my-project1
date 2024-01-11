package lk.ijse.fx.bo;


import lk.ijse.fx.bo.impl.*;


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
                return new AttendenceBOImpl();
            case CHILDREN:
                return new ChildrenBOImpl();
            case CHURCH:
                return new ChurchBOImpl();
            case EVENT:
                return new EventBOImpl();
            case FATHER:
                return new FatherBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            case SIGNUP:
                return new SignUpBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case VISIT:
                return new VisitBOImpl();
            default:
                return null;
        }
    }
}
