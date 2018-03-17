import Data.Char

-- Eleva um numero ao quadrado (aqui temos um comentario!)
doubleMe :: Int -> Int
doubleMe x = x^2

-- Verifica se um numero eh par (usa if/then/else para expressar funcao condicional)
-- mod: retorna resto da divisao inteira
isEven :: Int -> Bool
isEven n = if mod n 2 == 0 then True else False
-- Ou simplesmente:
-- isEven n = mod n 2 == 0

-- Gera um numero a partir de um caracter (usa outra estrutura condicional em Haskell: guardas)
encodeMe :: Char -> Int
encodeMe c
  | c == 'S'  = 0
  | c == 'N'  = 1
  | otherwise = 2

-- Calcula o quadrado do primeiro elemento da lista
-- Note que '[Int]' designa uma lista de elementos do tipo Int
doubleFirst :: [Int] -> Int
doubleFirst lis = (head lis)^2

-- Verifica se uma palavra tem mais de 2 caracteres
isLong :: String -> Bool -- isso é o mesmo que: isLong :: [Char] -> Bool
isLong s = length s > 2


--Prática 1

--1
somaQuad :: Int -> Int -> Int
somaQuad x y = (x^2) + (y^2)

--2
hasEqHeads :: [Int] -> [Int] -> Bool
hasEqHeads x y = if (head x) == (head y) then True else False

--3
catSuper :: [String] -> [String]
catSuper x = map ("Super "++) x

--4
contSpace :: String -> String
contSpace x = filter(== ' ') x

contStr :: String -> Int
contStr x = length(contSpace x)

--5
calAnonima :: [Float] -> [Float]
calAnonima x = map(\n->3*n^2 + 2/n + 1) x

--6
selectNeg :: [Int] -> [Int]
selectNeg x = filter(<0) x

--7
enTre :: [Int] -> [Int]
enTre list = filter(\n-> (n>0) && (n<101)) list

--8
idadeVelho :: [Int] -> [Int]
idadeVelho list = filter(\n-> (2018-n)>1980) list

--9
areEven :: [Int] -> [Int]
areEven list = filter(isEven) list 

--10
charFound :: Char -> String -> Bool
charFound a list = a `elem` list

--11
checkLast :: String -> Bool
checkLast c = if (last c) == 'a' then True else False

lastA :: [String] -> [String]
lastA list = filter (checkLast) list


--Prática 2

--1
isVowels :: Char -> Bool
isVowels c = c `elem` "aeiouAEIOU"

--2
addComma :: [String] -> [String]
addComma list = map (++ ",") list

--3
htmlListItems :: [String] -> [String]
htmlListItems list = map(\n-> "<LI>" ++ n ++ "</LI>") list

htmlCat :: String -> String
htmlCat x = "<LI>" ++ x ++ "</LI>"

htmlListItems2 :: [String] -> [String]
htmlListItems2 list = map(htmlCat) list

--4
semVogais :: String -> String
semVogais pal = filter (\c -> c `notElem` "aeiouAEIOU") pal

semVogais2 :: String -> String
semVogais2 pal = filter(not.isVowels) pal

--5
trocaLetra :: String -> String
trocaLetra t = map(\x->if x /= ' ' then '-' else ' ') t

isnotSpace :: Char -> Char
isnotSpace x = if x /= ' ' then '-' else ' '

trocaLetra2 :: String -> String
trocaLetra2 t = map(isnotSpace) t

--6
firstName :: String -> String
firstName n = takeWhile (/= ' ') n

--7
naoehNum :: Char -> Bool
naoehNum c = c `notElem` "0123456789"

isInt :: String -> Bool
isInt list = if length(filter (naoehNum) list) >0 then False else True

--8
lastName :: String -> String
lastName n = last(words n)

--9
userName :: String -> String
userName n = map (toLower)(take 1 n) ++ map (toLower)(lastName n)

--10
mapaLetras :: Char -> Char
mapaLetras x | x == 'a' = '4' | x == 'e' = '3' | x == 'i' = '2' | x == 'o' = '1' | x == 'u' = '0' | otherwise = x

encodeName :: String -> String
encodeName n = map (mapaLetras) n

--11
--Incompleto!
betterMapaLetras :: Char -> Char
betterMapaLetras x | x == 'a' = '4' | x == 'e' = '3' | x == 'i' = '1' | x == 'o' = '0' | otherwise = x

betterEncodeName :: String -> String
betterEncodeName n = map(betterMapaLetras) n 

--12
--Incompleto!
completaPal :: String -> String
completaPal x = if length(x) <10 then completaPal x++"."

auxPal :: String -> String
auxPal x = if length(x) >10 then (take 10 x) else completaPal x

stringDez :: [String] -> [String]
stringDez list = map(encodeName) list

