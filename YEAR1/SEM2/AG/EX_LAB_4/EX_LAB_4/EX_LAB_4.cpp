//#include <iostream>
//#include<fstream>
//#include<vector>
//
//using namespace std;
//ifstream fin("in.txt");
//ofstream gout("out.txt");
//
//
//
//bool eParinte(int parinte, int frunze, int nrNivele, vector<int>& parinti)
//{
//	for (int i = 0; i < nrNivele; i++)
//	{
//		if (i != frunze && parinti[i] == parinte)
//			return true;
//	}
//	return false;
//}
//
//
//
//int main()
//{
//
//	// codare Prüfer
//	vector<bool>esteparinte;
//	vector<int>parinte;
//	int n, x, i;
//	fin >> n;
//	gout << n - 1 << '\n';
//
//	for (i = 0; i < n; i++)
//	{
//		fin >> x;
//		parinte.push_back(x);
//		esteparinte.push_back(false);
//	}
//
//	for (i = 0; i < n; i++)
//	{
//		if (parinte[i] != -1)
//			esteparinte[parinte[i]] = true;
//	}
//
//	for (i = 0; i < n; i++) 
//		 if (!esteparinte[i] && parinte[i] != -1)
//		 {
//			 gout << parinte[i] << " ";
//			 if (!eParinte(parinte[i], i, n, parinte))
//				 esteparinte[parinte[i]] = false;
//			 parinte[i] = -1;
//			 i = -1;
//		 }
//	return 0;
//
//}
//
