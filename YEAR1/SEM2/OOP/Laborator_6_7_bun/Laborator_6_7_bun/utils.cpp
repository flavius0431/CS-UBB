#include "utils.h"

void BubbleSort(vector<Masina> all,Cmp cmp) {
	if (all.size() <= 1)return;
	bool sort = 0;
	while (!sort) {
		sort = 1;
		for (int i = 0; i < all.size() - 1; i++) {
			if (!cmp(all[i], all[i + 1])) {
				Masina m = all[i];
				all[i] = all[i + 1];
				all[i + 1] = m;
				sort = 0;
			}
		}
	}
}