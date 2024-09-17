#include <vector>
#include <stdexcept>
#include <iostream> // For testing purposes

class LegalBoard {
private:
    std::vector<bool> positiveDiagonals;
    std::vector<bool> negativeDiagonals;
    std::vector<bool> columns;
    std::vector<unsigned int> board;
    unsigned int firstEmptyRow;

public:
    LegalBoard(int n);

    bool isLegalNextMove(int col);

    void doNextMove(int col);

    void removeRow(int row);

    void nextLegalBoard();

    unsigned int nextMove();

    unsigned int nextMove(unsigned int lastMove);

    std::string boardToString();

    std::vector<unsigned int> getBoard();

    bool isSolution();

    int getNegDiagonal(int row, int col);

    int getPosDiagonal(int row, int col);
};
