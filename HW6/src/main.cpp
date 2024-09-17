#include <iostream>
#include "LegalBoard.h"
#include <time.h>

int main() {
    clock_t start_time = clock();
    for (int n = 4; n < 26; ++n) {
        LegalBoard legalBoard(n);
        legalBoard.nextLegalBoard();
        while (!legalBoard.isSolution()) {
            legalBoard.nextLegalBoard();
        }
        std::cout << "First solution to n = " << n << ": " << legalBoard.boardToString() << std::endl;
    }
    // Record the ending time
    clock_t end_time = clock();

    // Calculate the elapsed time
    double elapsed_time = (double)(end_time - start_time) / CLOCKS_PER_SEC;

    // Print the elapsed time
    printf("Elapsed time: %.4f seconds\n", elapsed_time);

    /*
    for (int n = 4; n < 13; ++n) {
        std::cout << "Every solution to n = " << n << ": " << std::endl;
        LegalBoard legalBoard(n);
        while (true) {
            try {
                legalBoard.nextLegalBoard();
                while (!legalBoard.isSolution()) {
                    legalBoard.nextLegalBoard();
                }
            } catch (const std::exception& e) {
                break;
            }
            std::cout << legalBoard.boardToString() << std::endl;
        }
    }*/

    return 0;
}
