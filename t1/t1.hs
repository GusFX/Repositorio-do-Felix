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

somaQuad :: Int -> Int -> Int
somaQuad x y = (x^2) + (y^2)

hasEqHeads :: [Int] -> [Int] -> Bool
hasEqHeads x y = if (head x) == (head y) then True else False

--irá subtrair a idade do ano atu

cat :: String -> String
cat x = "<LI>" ++ x ++ "</LI>"

catSuper :: [String] -> [String]
catSuper x = map (cat) x

contSpace :: String -> String
contSpace x = filter(== ' ') x

contStr :: String -> Int
contStr x = length(contSpace x)

calAnonima :: [Float] -> [Float]
calAnonima x = map(\n->3*n^2 + 2/n + 1) x

selectNeg :: [Int] -> [Int]
selectNeg x = filter(<0) x

enTre :: [Int] -> [Int]
enTre list = filter(\n-> (n>0) && (n<101)) list

idadeVelho :: [Int] -> [Int]
idadeVelho list = filter(\n-> (2018-n)>1980) list
 

--Outro dia, outros exercícios

isVowels :: Char -> Bool
isVowels x | x == 'a' = True | x == 'e' = True | x == 'i' = True | x == 'o' = True | x == 'u' = True | otherwise = False

isnotVowels :: Char -> Bool
isnotVowels x | x == 'a' = False | x == 'e' = False | x == 'i' = False | x == 'o' = False | x == 'u' = False | otherwise = True 

cat2 :: String -> String
cat2 x = x ++ "<LI>"

addComma :: [String] -> [String]
addComma list = map (cat2) list	

htmlListItems :: [String] -> [String]
htmlListItems list = map(cat) list

htmlListItems2 :: [String] -> [String]
htmlListItems2 list = map(\n-> "<LI>" ++ n ++ "</LI>") list

semVogais :: String -> String
semVogais pal = filter(isnotVowels) pal

isnotSpace :: Char -> Char
isnotSpace x = if x /= ' ' then '-' else ' '

trocaLetra :: String -> String
trocaLetra t = map(isnotSpace) t

trocaLetra2 :: String -> String
trocaLetra2 t = map(\x->if x /= ' ' then '-' else ' ') t 

firstName :: String -> String
firstName n = takeWhile (/= ' ') n


