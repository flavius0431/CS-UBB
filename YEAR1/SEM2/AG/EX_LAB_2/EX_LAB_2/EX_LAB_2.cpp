


#include <iostream>
#include<fstream>
#include<queue>
#include<vector>
#include<stack>
#include<algorithm>
#define inf 1<<30
using namespace std;

ifstream fin("graf.txt");


//dfs 1: 1 2 3 4 5

//sfd 1: 1 2 3 4 5

// labirint drum in labirint lab2_alg lui lee

int a[101][101];
int n;
int vf[101];
int dist[101];
int c[101], b[101][101];

vector<int>l[101];
vector<int>t[101];


void citire_graf(int a[101][101], int n)
{
    int m1, m2;
    while (fin >> m1 >> m2)
    {
        l[m1].push_back(m2);
        t[m2].push_back(m1);
        a[m1][m2] = b[m1][m2] = 1;
    }

}



void afisare_matrice(int a[101][101], int n)
{
   
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cout << a[i][j] << " ";
        }
        cout << '\n';
    }

}

void matricea_inchiderii_tranzitive(int a[101][101], int n)
{
    for (int k = 1; k <= n; k++)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (a[i][j] == 0)
                    a[i][j] = (a[i][k] && a[k][j]);
            }
        }
    }
}


void copie_matrice(int a[101][101], int b[101][101], int n)
{
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            b[i][j] = a[i][j];
        }
    }
}

vector<int> bfs( int start,int stop) {
    for (int i = 1; i <= n; i++) {
        dist[i] = inf;
    }
    queue<int> q;
    q.push(start);
    dist[start] = 0;
    while (!q.empty()){
        int nod = q.front();
        q.pop();
        for (int vecin : l[nod]) {
            if (dist[vecin] == inf) {
                q.push(vecin);
                dist[vecin] = dist[nod] + 1;

            }

        }

    }

    vector<int>v;

    if (dist[stop] == inf)
        return v;
    v.push_back(stop);
  
    int nod = stop;
    while (nod != start) {
        for (int vecin : t[nod]) {
            if (dist[vecin] == dist[nod] - 1) {
                v.push_back(vecin);
                nod = vecin;
                break;
            }
        }
    }


    return v;
}

//dfs 1: 1 2 3 4 5

vector<int> dfs(int x)
{
    vector<int> rez;
    stack<int> s;
    s.push(x);
    c[x] = 1;
    while (!s.empty())
    {
        x = s.top();
        s.pop();
        rez.push_back(x);
        int i;
        for (i = 1; i <= n; i++)
        {
            if (b[x][i] == 1 && c[i] == 0)
            {
                s.push(i);
                c[i] = 1;

            }
        }
    }
    return rez;
}

vector<int> bfs1(int start)
{
    for (int i = 1; i <= n; i++)
    {
        dist[i] = inf;
    }
    queue<int> q;
    vector<int> v;
    q.push(start);
    dist[start] = 0;
    while (!q.empty())
    {
        int nod = q.front();
        q.pop();
        v.push_back(nod);
        for (int vecin : l[nod])
        {
            if (dist[vecin] == inf)
            {
                q.push(vecin);
                dist[vecin] = dist[nod] + 1;
            }

        }

    }
    return v;
}



int main()
{
    fin >> n;
    citire_graf(a, n);
    copie_matrice(a, b, n);
    
    //determina matricerea inchiderii tranzitive( 1, daca exista drum , oricare 2 noduri)//0,altfel
    cout << "matricea inchiderii tranzitive\n";
    matricea_inchiderii_tranzitive( b, n);
    afisare_matrice(b, n);


    //identificarea unui drum minim intre minim 2 varfuri date(alg lui moore bfs? 1,5)
    cout << "bfs-algoritmul lui moore\n";
    int start, stop;
    cout << "start: ";
    cin >> start;
    cout << "stop: ";
    cin >> stop;
    vector<int> v = bfs(start,stop);
    reverse(v.begin(), v.end());
    if (v.size() == 0) {
        cout << "nu exista drum!";
    }
    else {
        for (int nod : v)
            cout << nod<<" ";
    }
    
    //dfs 1: 1 2 3 4 5
    cout << "\ndfs\n";
    int i;
    cout << "varful de la care plecam: ";
    cin >> i;
    vector<int>rez = dfs(i);
    sort(rez.begin(), rez.end());
    for (int nod : rez) {
        cout << nod <<" ";
    }

    //bfs 1: 1 2 3 4 5
    cout << "\nbfs\n";
    int k;
    cout << "varful de la care plecam: ";
    cin >> k;
    vector<int>rez1 = bfs1(k);
    sort(rez1.begin(), rez1.end());
    for (int nodu : rez1) {
        
        cout << nodu << " " << dist[nodu] << ", ";
    }


   
}

