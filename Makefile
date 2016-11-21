CXXFLAGS = -ansi -Wall -g -std=c++98
ORDER = main.o
debug = SETTODEBUGTOGETDEBUG

fold: $(ORDER)
	g++ $(CXXFLAGS) $(ORDER) -o fold

main.o: main.cpp substructure.h pair.h
	g++ $(CXXFLAGS) -c main.cpp

# Ignore the next two goals, I copy my makefiles around so
# I don't have to keep looking up what options I want
objIO.o: objIO.cpp objIO.h
	g++ $(CXXFLAGS) -D $(debug) -c objIO.cpp

slVector.o: slVector.cpp slVector.H slIO.H
	g++ $(CXXFLAGS) -c slVector.cpp

run:
	./fold test100.txt

ten:
	./fold test10.txt

fifty:
	./fold test100.txt 50

thousand:
	./fold test1000.txt

clean:
	rm -rf *.o
	rm -rf fold
