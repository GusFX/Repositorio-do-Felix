--1
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = []
geraTabela n = (n,n*n):(geraTabela (n-1))
 
--2
contido :: Char -> [Char] -> Bool
contido _ [] = False
contido c (x:xs)
  | (x == c) = True
  | otherwise = contido c xs
 
--3
translate :: [(Float,Float)] -> [(Float,Float)]
translate [] = []
translate list = (addDois (head list)):(translate (tail(list)))
 
addDois :: (Float,Float) -> (Float,Float)
addDois (x,y) = (x+2, y+2)
 
--4
geraTabela' :: Int -> [(Int,Int)]
geraTabela' x = inverte (geraTabela x)
 
inverte :: [(Int,Int)] -> [(Int,Int)]
inverte [] = []
inverte (x:xs) = (inverte xs)++[x]

