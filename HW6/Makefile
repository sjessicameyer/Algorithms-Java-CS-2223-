CXX = g++
CXXFLAGS = -std=c++11 -Wall -Wextra
SRC_DIR = src
BUILD_DIR = build
BIN_DIR = bin
TARGET = nQueensProblem

SRCS = $(wildcard $(SRC_DIR)/*.cpp)
OBJS = $(SRCS:$(SRC_DIR)/%.cpp=$(BUILD_DIR)/%.o)

all: $(BIN_DIR)/$(TARGET)

$(BIN_DIR)/$(TARGET): $(OBJS)
	@mkdir -p $(BIN_DIR)
	$(CXX) $(CXXFLAGS) $^ -o $@

$(BUILD_DIR)/%.o: $(SRC_DIR)/%.cpp
	@mkdir -p $(BUILD_DIR)
	$(CXX) $(CXXFLAGS) -c $< -o $@

.PHONY: clean

clean:
	rm -rf $(BUILD_DIR) $(BIN_DIR)
