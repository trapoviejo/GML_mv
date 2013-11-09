public class MemoriaCte
{
	int [] cteInt;
	float [] cteFloat;
	Pos [] ctePos; 
	String[] cteString; 
	Boolean[] cteBoolean;
	int[] cteEntity;
	int[] cteList;
	public MemoriaCte(int dimCteInt, int dimCteFloat, int dimCtePos, int dimCteString, int dimCteBoolean, int dimCteEntity, int dimCteList)
	{
		cteInt = new int[dimCteInt];
		cteFloat = new float[dimCteFloat];
		ctePos = new Pos[dimCtePos];
		cteString = new String[dimCteString];
		cteBoolean = new Boolean[dimCteBoolean];
		cteEntity = new int[dimCteEntity];
		cteList = new int[dimCteList];
	}

}
