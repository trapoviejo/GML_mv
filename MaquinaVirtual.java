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

    public static final int OFFSET_GLOBAL_VAR_INT       = 100;
    public static final int OFFSET_GLOBAL_VAR_FLOAT     = 1000;
    public static final int OFFSET_GLOBAL_VAR_POS       = 2000;
    public static final int OFFSET_GLOBAL_VAR_STRING    = 3000;
    public static final int OFFSET_GLOBAL_VAR_BOOLEAN   = 4000;
    public static final int OFFSET_GLOBAL_VAR_ENTITY    = 5000;
    public static final int OFFSET_GLOBAL_VAR_LIST      = 6000;

    public static final int OFFSET_GLOBAL_TEMP_INT      = 7000;
    public static final int OFFSET_GLOBAL_TEMP_FLOAT    = 8000;
    public static final int OFFSET_GLOBAL_TEMP_POS      = 9000;
    public static final int OFFSET_GLOBAL_TEMP_STRING   = 10000;
    public static final int OFFSET_GLOBAL_TEMP_BOOLEAN  = 11000;
    public static final int OFFSET_GLOBAL_TEMP_ENTITY   = 12000;
    public static final int OFFSET_GLOBAL_TEMP_LIST     = 13000;

    public static final int OFFSET_GLOBAL_CTE_INT       = 14000;
    public static final int OFFSET_GLOBAL_CTE_FLOAT     = 15000;
    public static final int OFFSET_GLOBAL_CTE_POS       = 16000;
    public static final int OFFSET_GLOBAL_CTE_STRING    = 17000;
    public static final int OFFSET_GLOBAL_CTE_BOOLEAN   = 18000;
    public static final int OFFSET_GLOBAL_CTE_ENTITY    = 19000;
    public static final int OFFSET_GLOBAL_CTE_LIST      = 20000;
    
    public static final int OFFSET_LOCAL_VAR_INT       = 21000;
    public static final int OFFSET_LOCAL_VAR_FLOAT     = 22000;
    public static final int OFFSET_LOCAL_VAR_POS       = 23000;
    public static final int OFFSET_LOCAL_VAR_STRING    = 24000;
    public static final int OFFSET_LOCAL_VAR_BOOLEAN   = 25000;
    public static final int OFFSET_LOCAL_VAR_ENTITY    = 26000;
    public static final int OFFSET_LOCAL_VAR_LIST      = 27000;

    public static final int OFFSET_LOCAL_TEMP_INT      = 28000;
    public static final int OFFSET_LOCAL_TEMP_FLOAT    = 29000;
    public static final int OFFSET_LOCAL_TEMP_POS      = 30000;
    public static final int OFFSET_LOCAL_TEMP_STRING   = 31000;
    public static final int OFFSET_LOCAL_TEMP_BOOLEAN  = 32000;
    public static final int OFFSET_LOCAL_TEMP_ENTITY   = 33000;
    public static final int OFFSET_LOCAL_TEMP_LIST     = 34000;


    //global, temporal, constante
    int contIntG=0, contIntT=0, contIntC=0;
    int contFloatG=0, contFloatT=0, contFloatC=0;
    int contPosG=0, contPosT=0, contPosC=0;
    int contStringG=0, contStringT=0, contStringC=0;
    int contBooleanG=0,  contBooleanC=0, contBooleanT=0;
    int contEntityG=0, contEntityT=0, contEntityC=0;
    int contListG=0, contListT=0, contListC=0;
    
    Memoria memGlobal = new Memoria();
    MemoriaConstantes 
    Stack<Memoria> memLocal = new Stack<Memoria>();
    
    int getType(int direccion)
    {
        int mvdir = 0;
        
        //GLOBALES VARIABLES
        if(direccion >= OFFSET_GLOBAL_VAR_INT && direccion < OFFSET_GLOBAL_VAR_FLOAT)
        {
            return TIPO_INT;
        }
        else if(direccion < OFFSET_GLOBAL_VAR_POS)
        {
            return TIPO_FLOAT;
        }
        else if(direccion < OFFSET_GLOBAL_VAR_STRING)
        {
            return TIPO_POS;
        }
        else if(direccion < OFFSET_GLOBAL_VAR_BOOLEAN)
        {
            return TIPO_STRING;
        }
        else if(direccion < OFFSET_GLOBAL_VAR_ENTITY)
        {
            return TIPO_BOOLEAN;
        }
        else if(direccion < OFFSET_GLOBAL_VAR_LIST)
        {
            return TIPO_ENTITY;
        }
        else if(direccion < OFFSET_GLOBAL_TEMP_INT)
        {
            return TIPO_LIST;
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
    
    public static void suma(int op1, int op2, int res){
        System.out.println("+");
        
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
                int op1 = Integer.parseInt(esteCuadruplo[1]);
                int op2 = Integer.parseInt(esteCuadruplo[2]);
                int res = Integer.parseInt(esteCuadruplo[3]);
                switch(operador){
                    case OP_SUMA:
                        suma(op1, op2, res);
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
        
    }
}
