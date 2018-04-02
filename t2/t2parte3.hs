import Data.Char

isEanOk :: String -> Bool
isEanOk str = if (length str) == 13 then digitToInt(last str) == checaUltimo str else False

checaUltimo :: String -> Int
checaUltimo num  = 10 - (mod (somaTudo num) 10)	

somaTudo :: String -> Int
somaTudo num = sum (zipWith (*) (string2intlist num) [1,3,1,3,1,3,1,3,1,3,1,3])

string2intlist :: String -> [Int]
string2intlist str = map (digitToInt) str
