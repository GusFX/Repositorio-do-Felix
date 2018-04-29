/*   
     Problema: CD Independente (https://olimpiada.ic.unicamp.br/static/extras/obi2017/provas/ProvaOBI2017_f1i2.pdf)
     Uma banda formada por alunos e alunas da escola está gravando um CD com exatamente sete músicas
     distintas – S, T, V, W, X, Y e Z. Cada música ocupa exatamente uma das sete faixas contidas no
     CD. Algumas das músicas são sucessos antigos de rock; outras são composições da própria banda. As
     seguintes restrições devem ser obedecidas:
     - S ocupa a quarta faixa do CD.
     - Tanto W como Y precedem S no CD (ou seja, W e Y estão numa faixa que é tocada antes de S no CD).
     - T precede W no CD (ou seja, T está numa faixa que é tocada antes de W).
     - Um sucesso de rock ocupa a sexta faixa do CD.
     - Cada sucesso de rock é imediatamente precedido no CD por uma composição da banda (ou seja,
     no CD cada sucesso de rock toca imediatamente após uma composição da banda).
     - Z é um sucesso de rock.
*/

sucesso(z).

regra1(CD) :- 
    CD = [_,_,_,s,_,_,_].
    
regra2(CD) :-
    nth0(Id1,CD,s),
    nth0(Id2,CD,w),
    nth0(Id3,CD,y),
    Id1>Id2,
    Id1>Id3.

regra3(CD) :-
    nth0(Id1,CD,w),
    nth0(Id2,CD,t),
    Id1>Id2.

regra4(CD) :-
    nth0(5,CD,X),
    sucesso(X).

ordemCerta(CD) :-
	CD=[_,_,_,_,_,_,_],
    regra1(CD),
    regra2(CD),
    regra3(CD),
    regra4(CD).
    
aolado(X,Y,CD):-
    nextto(X,Y,CD);
    nextto(Y,X,CD).
    
necessariamenteComposicao(X,CD) :-
    nextto(X,Y,CD),
    sucesso(Y).

/*
Questao 11. Qual das seguintes alternativas poderia
ser a ordem das m´usicas no CD, da primeira
para a s´etima faixa?
(A) T, W, V, S, Y, X, Z
(B) V, Y, T, S, W, Z, X
(C) X, Y, W, S, T, Z, S
(D) Y, T, W, S, X, Z, V
(E) Z, T, X, W, V, Y, S
*/

%ordemCerta([t,w,v,s,y,x,z]).
%ordemCerta([v,y,t,s,w,z,x]).
%ordemCerta([x,y,w,s,t,z,s]).
%ordemCerta([y,t,w,s,x,z,v]).
%ordemCerta([z,t,x,w,v,y,s]).

/*
Questao 13. Qual das seguintes m´usicas ´e necessariamente
uma composi¸c˜ao da banda?
(A) S
(B) T
(C) X
(D) Y
(E) W
*/
%ordemCerta([y,t,w,s,x,z,v]), necessariamenteComposicao(s,[y,t,w,s,x,z,v]).
%ordemCerta([y,t,w,s,x,z,v]), necessariamenteComposicao(t,[y,t,w,s,x,z,v]).
%ordemCerta([y,t,w,s,x,z,v]), necessariamenteComposicao(x,[y,t,w,s,x,z,v]).
%ordemCerta([y,t,w,s,x,z,v]), necessariamenteComposicao(y,[y,t,w,s,x,z,v]).
%ordemCerta([y,t,w,s,x,z,v]), necessariamenteComposicao(w,[y,t,w,s,x,z,v]).
