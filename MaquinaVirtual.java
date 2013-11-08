import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.util.ArrayList;

public class MaquinaVirtual
{      

    public static final int TIPO_INT = 10000;
    public static final int TIPO_FLOAT = 10001;
    public static final int TIPO_POS = 10002;
    public static final int TIPO_STRING = 10003;
    public static final int TIPO_BOOLEAN = 10004;
    public static final int TIPO_ENTITY = 10005;
    public static final int TIPO_LIST = 10006;
    public static final int TIPO_VOID = 19998;
    public static final int TIPO_PROGRAMA = 19999;  
    public static final int OP_OR = 20000;
    public static final int OP_AND = 20001;
    public static final int OP_MAYOROIGUALQUE = 20002;
    public static final int OP_MENOROIGUALQUE = 20003;
    public static final int OP_MAYORQUE = 20004;
    public static final int OP_MENORQUE = 20005;
    public static final int OP_IGUAL = 20006;
    public static final int OP_DIFERENTE = 20007;
    public static final int OP_SUMA = 20008;
    public static final int OP_RESTA = 20009;
    public static final int OP_MULTIPLICACION = 20010;
    public static final int OP_DIVISION = 20011;
    public static final int OP_X = 20012; 
    public static final int OP_Y = 20013;
    public static final int OP_ASIGNACION = 21000;
    public static final int OP_GOTO = 21001;
    public static final int OP_GOTOF = 21002;
    public static final int OP_GOTOV = 21003;
    public static final int OP_ENDPROC = 21004;
    public static final int OP_ERA = 21005;
    public static final int OP_PARAM = 21006;
    public static final int OP_GOSUB = 21007;


    //global, temporal, constante
    int contIntG=0, contIntT=0, contIntC=0;
    int contFloatG=0, contFloatT=0, contFloatC=0;
    int contPosG=0, contPosT=0, contPosC=0;
    int contStringG=0, contStringT=0, contStringC=0;
    int contBooleanG=0,  contBooleanC=0, contBooleanT=0;
    int contEntityG=0, contEntityT=0, contEntityC=0;
    int contListG=0, contListT=0, contListC=0;
    
    Vector enterosGlobales = new Vector();
    Vector enterosLocales = new Vector();
    Vector enterosTemporales = new Vector();
    Vector enterosConstantes = new Vector();
    
    Vector floatsGlobales = new Vector();
    Vector floatsLocales = new Vector();
    Vector floatsTemporales = new Vector();
    Vector floatsConstantes = new Vector();
    
    Vector posGlobales = new Vector();
    Vector posLocales = new Vector();
    Vector posTemporales = new Vector();
    Vector posConstantes = new Vector();

    Stack pilaEjecucion = new Stack();

    int getMVDir(int direccion)
    {
        int mvdir = 0;
        
        //GLOBALES VARIABLES
        if(direccion >= 0 && direccion <= 999)
        {
            mvdir = TIPO_INT;
        }
        else if(direccion >= 1000 && direccion <= 1999)
        {
            mvdir = TIPO_FLOAT;
        }
        else if(direccion >= 2000 && direccion <= 2999)
        {
            mvdir = TIPO_POS;
        }
        else if(direccion >= 3000 && direccion <= 3999)
        {
            mvdir = TIPO_STRING;
        }
        else if(direccion >= 4000 && direccion <= 4999)
        {
            mvdir = TIPO_BOOLEAN;
        }
        else if(direccion >= 5000 && direccion <= 5999)
        {
            mvdir = TIPO_ENTITY;
        }
        else if(direccion >= 6000 && direccion <= 6999)
        {
            mvdir = TIPO_LIST;
        }
        //GLOBALES TEMPORALES
        else if(direccion >= 7000 && direccion <= 7999)
        {
            mvdir = TIPO_INT;
        }
        else if(direccion >= 8000 && direccion <= 8999)
        {
            mvdir = TIPO_FLOAT;
        }
        else if(direccion >= 9000 && direccion <= 9999)
        {
            mvdir = TIPO_POS;
        }
        else if(direccion >= 10000 && direccion <= 10999)
        {
            mvdir = TIPO_STRING;
        }
        else if(direccion >= 11000 && direccion <= 11999)
        {
            mvdir = TIPO_BOOLEAN;
        }
        else if(direccion >= 12000 && direccion <= 12999)
        {
            mvdir = TIPO_ENTITY;
        }
        else if(direccion >= 13000 && direccion <= 13999)
        {
            mvdir = TIPO_LIST;
        }    
        //GLOBALES CONSTANTES
        else if(direccion >= 14000 && direccion <= 14999)
        {
            mvdir = TIPO_INT;
        }
        else if(direccion >= 15000 && direccion <= 15999)
        {
            mvdir = TIPO_FLOAT;
        }
        else if(direccion >= 16000 && direccion <= 16999)
        {
            mvdir = TIPO_POS;
        }
        else if(direccion >= 17000 && direccion <= 17999)
        {
            mvdir = TIPO_STRING;
        }
        else if(direccion >= 18000 && direccion <= 18999)
        {
            mvdir = TIPO_BOOLEAN;
        }
        else if(direccion >= 19000 && direccion <= 19999)
        {
            mvdir = TIPO_ENTITY;
        }
        else if(direccion >= 20000 && direccion <= 20999)
        {
            mvdir = TIPO_LIST;
        }
        //LOCALES VARIABLES
        else if(direccion >= 21000 && direccion <= 21999)
        {
            mvdir = TIPO_INT;
        }
        else if(direccion >= 22000 && direccion <= 22999)
        {
            mvdir = TIPO_FLOAT;
        }
        else if(direccion >= 23000 && direccion <= 23999)
        {
            mvdir = TIPO_POS;
        }
        else if(direccion >= 24000 && direccion <= 24999)
        {
            mvdir = TIPO_STRING;
        }
        else if(direccion >= 25000 && direccion <= 25999)
        {
            mvdir = TIPO_BOOLEAN;
        }
        else if(direccion >= 26000 && direccion <= 26999)
        {
            mvdir = TIPO_ENTITY;
        }
        else if(direccion >= 27000 && direccion <= 27999)
        {
            mvdir = TIPO_LIST;
        }
        //LOCALES TEMPORALES
        else if(direccion >= 28000 && direccion <= 28999)
        {
            mvdir = TIPO_INT;
        }
        else if(direccion >= 29000 && direccion <= 29999)
        {
            mvdir = TIPO_FLOAT;
        }
        else if(direccion >= 30000 && direccion <= 31999)
        {
            mvdir = TIPO_POS;
        }
        else if(direccion >= 32000 && direccion <= 32999)
        {
            mvdir = TIPO_STRING;
        }
        else if(direccion >= 33000 && direccion <= 33999)
        {
            mvdir = TIPO_BOOLEAN;
        }
        else if(direccion >= 34000 && direccion <= 34999)
        {
            mvdir = TIPO_ENTITY;
        }
        else if(direccion >= 35000 && direccion <= 35999)
        {
            mvdir = TIPO_LIST;
        }
        //LOCALES CONSTANTES
        else if(direccion >= 36000 && direccion <= 36999)
        {
            mvdir = TIPO_INT;
        }
        else if(direccion >= 37000 && direccion <= 37999)
        {
            mvdir = TIPO_FLOAT;
        }
        else if(direccion >= 38000 && direccion <= 38999)
        {
            mvdir = TIPO_POS;
        }
        else if(direccion >= 39000 && direccion <= 39999)
        {
            mvdir = TIPO_STRING;
        }
        else if(direccion >= 40000 && direccion <= 40999)
        {
            mvdir = TIPO_BOOLEAN;
        }
        else if(direccion >= 41000 && direccion <= 41999)
        {
            mvdir = TIPO_ENTITY;
        }
        else if(direccion >= 42000 && direccion <= 42999)
        {
            mvdir = TIPO_LIST;
        }
        return mvdir;
    }

    public static void main(String args[])throws IOException
    {
    
        ArrayList<String> listaCuadruplos = new ArrayList<String>();
        //lee archivo, almacena cuadruplo en arraylist
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String[] esteCuadruplo;
            br = new BufferedReader(new FileReader("cuadruplos.txt"));
            while ((sCurrentLine = br.readLine()) != null) 
            {
                //parser
                //listaCuadruplos.add(sCurrentLine);
                esteCuadruplo = sCurrentLine.split(" ");
                int operador = Integer.parseInt(esteCuadruplo[0]);
                switch(operador){
                    case OP_SUMA:
                        System.out.println("+");
                        break;
                    case OP_RESTA:
                        System.out.println("-");
                        break;
                    case OP_MULTIPLICACION:
                        System.out.println("*");
                        break;
                    case OP_DIVISION:
                        System.out.println("/");
                        break;
                    case OP_X:
                        System.out.println("_X");
                        break;
                    case OP_Y:
                        System.out.println("_Y");
                        break;
                    case OP_ASIGNACION:
                        System.out.println("=");
                        break;
                    case OP_OR:
                        System.out.println("||");
                        break;
                    case OP_AND:
                        System.out.println("&&");
                        break;
                    case OP_MAYOROIGUALQUE:
                        System.out.println(">=");
                        break;
                    case OP_MENOROIGUALQUE:
                        System.out.println("<=");
                        break;
                    case OP_MAYORQUE:
                        System.out.println(">");
                        break;
                    case OP_MENORQUE:
                        System.out.println("<");
                        break;
                    case OP_IGUAL:
                        System.out.println("=");
                        break;
                    case OP_DIFERENTE:
                        System.out.println("!=");
                        break;
                    case OP_GOTO:
                        System.out.println("GOTO");
                        break;
                    case OP_GOTOF:
                        System.out.println("GOTOF");
                        break;
                    case OP_GOTOV:
                        System.out.println("GOTOV");
                        break;
                    case OP_ENDPROC:
                        System.out.println("ENDPROC");
                        break;
                    case OP_ERA:
                        System.out.println("ERA");
                        break;
                    case OP_PARAM:
                        System.out.println("PARAM");
                        break;
                    case OP_GOSUB:
                        System.out.println("GOSUB");
                        break;
                    default:
                        break;
                }
            }
        } 
        catch (IOException e) 
        {
         e.printStackTrace();
        } 
        finally
        {
            try 
            {
                if (br != null)br.close();
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
        for(int i=0; i < listaCuadruplos.size();i++)
        {
           // System.out.println(listaCuadruplos[i]);
        }
        
    }
}