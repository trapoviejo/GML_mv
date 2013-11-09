public class Memoria
{
	int [] varInt, tempInt,  varString, tempString, varBoolean, tempBoolean, varEntity, tempEntity, varList, tempList;
	float [] varFloat, tempFloat;
	Pos [] varPos, tempPos;
	public Memoria(int dimVarInt, int dimTempInt, int dimVarFloat, int dimTempFloat, int dimVarPos, int dimTempPos, int dimVarString, int dimTempString,
	 int dimVarBoolean, int dimTempBoolean, int dimVarEntity, int dimTempEntity, int dimVarList, int dimTempList)
	{
		varInt = new int[dimVarInt];
		tempInt = new int[dimTempInt];
		varFloat = new float[dimVarFloat];
		tempFloat = new float[dimTempFloat];
		varPos = new Pos[dimVarPos];
		tempPos = new Pos[dimTempPos];
		varString = new int[dimVarString];
		tempString = new int[dimTempString];
		varBoolean = new int[dimVarBoolean];
		tempBoolean = new int[dimTempBoolean];
		varEntity = new int[dimVarEntity];
		tempEntity = new int[dimTempEntity];
		varList = new int[dimVarList];
		tempList = new int[dimTempList];
	}

}
