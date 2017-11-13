#HW 4 Makefile - Grant Messner
TEX = pdflatex

Ph.20Assignment3.pdf : Ph.20Assignment3.tex explicitEuler.pdf trueState.pdf errorPlot1.pdf errorvsH.pdf energyPlot.pdf energyPlot.pdf implicitEuler.pdf errorPlot2.pdf errorvsH2.pdf energy2.pdf XvsVexplicit.pdf XvsVimplicit.pdf XvsVtrue.pdf XvsVsymp.pdf sympError.pdf
	$(TEX) Ph.20Assignment3.tex

explicitEuler.pdf : Ph.20Assignment3.py 
	python Ph.20Assignment3.py 0

trueState.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 1

errorPlot1.pdf : Ph.20Assignment3.py 
	python Ph.20Assignment3.py 2

errorvsH.pdf : Ph.20Assignment3.py 
	python Ph.20Assignment3.py 3

energyPlot.pdf : Ph.20Assignment3.py 
	python Ph.20Assignment3.py 4

implicitEuler.pdf : Ph.20Assignment3.py 
	python Ph.20Assignment3.py 5

errorPlot2.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 6

errorvsH2.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 7

energy2.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 8

XvsVexplicit.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 9

XvsVimplicit.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 10

XvsVtrue.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 11

XvsVsymp.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 12

sympError.pdf : Ph.20Assignment3.py
	python Ph.20Assignment3.py 13
