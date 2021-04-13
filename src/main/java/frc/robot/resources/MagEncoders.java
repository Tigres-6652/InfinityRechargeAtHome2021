/**El número de pulsos utilizados en el cálculo de la distancia por pulso no depende del tipo de decodificación - 
 cada "pulso" siempre debe considerarse como un ciclo completo (cuatro bordes). **/

package frc.robot.resources;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;


/** Add your docs here. */
public class MagEncoders {

    public Encoder rightEncoder, leftEncoder;


    public MagEncoders(){

        /** 1X Decoding:  Incrementa la distancia para cada período completo de la señal del codificador (una vez por cuatro bordes).

        2X Decoding: Incrementa la distancia por cada medio período de la señal del codificador (una vez por dos bordes).

        4X Decoding: Incrementa la distancia para cada borde de la señal del codificador (cuatro veces por período).*/
        try{
        rightEncoder = new Encoder(0,1,false,EncodingType.k4X);
        leftEncoder = new Encoder(0,1,false,EncodingType.k4X);
        } catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    public double getRightEncoderDistance(){
        double RightEncoderDistance;
        //Hay 256 pulsos por rotacion del enconder, asignamos que el robot avanza un pie por cada rotacion
        rightEncoder.setDistancePerPulse(1./256.);
        
        RightEncoderDistance = rightEncoder.getDistance();
        return RightEncoderDistance;
    }

    public double getLeftEncoderDistance(){
        double LeftEncoderDistance;
        //Hay 256 pulsos por rotacion del enconder, asignamos que el robot avanza un pie por cada rotacion
        leftEncoder.setDistancePerPulse(1./256.);

        LeftEncoderDistance = leftEncoder.getDistance();
        return LeftEncoderDistance;
    }
    //Devuelve el promedio de ambos encoders
    public double getMagEncodersDistance(double rightDistance, double leftDistance){
    
        double encodersDistance = ((rightDistance + leftDistance)/2);
        return encodersDistance;
    }


}
