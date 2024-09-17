#include "LegalBoard.h"

LegalBoard::LegalBoard(int n) : positiveDiagonals(2 * n - 1), negativeDiagonals(2 * n - 1), columns(n), board(n), firstEmptyRow(0) {}

bool LegalBoard::isLegalNextMove(int col) {
    int posDiagonal = getPosDiagonal(firstEmptyRow, col);
    int negDiagonal = getNegDiagonal(firstEmptyRow, col);

    return !(positiveDiagonals[posDiagonal] || negativeDiagonals[negDiagonal] || columns[col]);
}

void LegalBoard::doNextMove(int col) {
    board[firstEmptyRow] = col;

    int posDiagonal = getPosDiagonal(firstEmptyRow, col);
    int negDiagonal = getNegDiagonal(firstEmptyRow, col);

    columns[col] = true;
    positiveDiagonals[posDiagonal] = true;
    negativeDiagonals[negDiagonal] = true;

    firstEmptyRow += 1;
}

void LegalBoard::removeRow(int row) {
    int col = board[row];

    int posDiagonal = getPosDiagonal(row, col);
    int negDiagonal = getNegDiagonal(row, col);

    columns[col] = false;
    positiveDiagonals[posDiagonal] = false;
    negativeDiagonals[negDiagonal] = false;

    board[row] = 0;
    firstEmptyRow -= 1;
}

void LegalBoard::nextLegalBoard() {
    unsigned int move = nextMove();
    while (!isLegalNextMove(move)) {
        move = nextMove(move);
    }
    doNextMove(move);
}

unsigned int LegalBoard::nextMove() {
    if (isSolution()) {
        return nextMove(board[firstEmptyRow - 1]);
    }

    return 1;
}

unsigned int LegalBoard::nextMove(unsigned int lastMove) {
    if (lastMove < board.size()) {
        return lastMove + 1;
    } else {
        if (firstEmptyRow == 0) {
            throw std::runtime_error("No next move exists");
        } else {
            int currentRow = firstEmptyRow - 1;
            unsigned int next = board[currentRow];
            removeRow(currentRow);
            return nextMove(next);
        }
    }
}

std::string LegalBoard::boardToString() {
    std::string result = "[";
    for (unsigned int i = 0; i < board.size(); ++i) {
        result += std::to_string(board[i]);
        if (i < board.size() - 1) {
            result += ", ";
        }
    }
    result += "]";
    return result;
}

std::vector<unsigned int> LegalBoard::getBoard() {
    return board;
}

bool LegalBoard::isSolution() {
    return board[board.size() - 1] != 0;
}

int LegalBoard::getNegDiagonal(int row, int col) {
    return row + board.size() - col;
}

int LegalBoard::getPosDiagonal(int row, int col) {
    return row + col - 1;
}
