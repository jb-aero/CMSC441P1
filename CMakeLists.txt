cmake_minimum_required(VERSION 3.6)
project(CMSC441)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++98")

set(SOURCE_FILES main.cpp)
add_executable(CMSC441 ${SOURCE_FILES})