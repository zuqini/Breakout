MAIN=Breakout
JVM=java
JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<

run: 
	$(JAVAC) $(sources) && $(JVM) $(MAIN)