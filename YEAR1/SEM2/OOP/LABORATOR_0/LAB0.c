
#include <stdio.h>
//problema 1

int main() {

    int n;
    int s = 0;
    int nr;
    printf("Introduceti nl:");
    scanf("%d", &n);
    for (int i = 1; i <= n; i++)
    {
        scanf("%d", &nr);
        s = s + nr;
    }
    printf("%d", s);

    return 0;


}

