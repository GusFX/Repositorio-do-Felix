/*Problema: Bicho-preguiça (https://olimpiada.ic.unicamp.br/static/extras/obi2013/provas/ProvaOBI2013_f1i1.pdf)
O Zoológico de São Paulo fez uma pesquisa pela internet para escolher o nome de seu mais novo
habitante, recém-nascido: um lindo e simpático bicho-preguiça. Sete nomes foram colocados na
pesquisa: Buda, Fofo, Pingo, Rock, Samba, Teco e Zorro. O Zoológico ordenou os nomes de acordo
com o número de votos recebidos, do mais votado para o menos votado. O ganhador ainda não foi
anunciado, mas alguns dados da pesquisa foram divulgados:
• Cada nome recebeu um número diferente de votos.
• Buda recebeu mais votos do que Teco.
• Teco recebeu mais votos do que Fofo.
• Fofo recebeu mais votos do que Rock.
• Samba não recebeu o menor número de votos.
• Zorro recebeu menos votos do que Pingo.
• Zorro recebeu mais votos do que Samba e mais votos do que Teco
*/

regra2(L):-
    nth0(Id1,L,buda),
    nth0(Id2,L,teco),
    Id1<Id2.

regra3(L):-
    nth0(Id1,L,teco),
    nth0(Id2,L,fofo),
    Id1<Id2.

regra4(L):-
    nth0(Id1,L,fofo),
    nth0(Id2,L,rock),
    Id1<Id2.

regra5(L):-
    nth0(Id,L,samba),
    Id<6.
