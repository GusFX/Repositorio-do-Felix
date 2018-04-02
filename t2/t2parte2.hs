--1
isBin :: String -> Bool
isBin [] = True
isBin (x:xs) = if (x == '0' || x == '1') then isBin xs else False	

--2
isBin' :: String -> Bool
isBin' list = length(filter(`notElem` "01") list) == 0

--3
bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] _ = 0
auxBin2Dec (x:xs) n = x*(2^n) + auxBin2Dec xs (n-1)

--4
bin2dec' :: [Int] -> Int
bin2dec' [] = undefined
bin2dec' list = sum(zipWith (\a b -> a*(2^b)) list [(length list)-1, (length list)-2 .. 0])

--5
dec2bin :: Int -> [Int]
dec2bin 0 = [0]
dec2bin num = reverse(toBin(num))

toBin :: Int -> [Int]
toBin 0 = []
toBin num = (if (mod num 2 == 1) then 1 else 0): toBin(div num 2) 

--6
isHex :: String -> Bool
isHex [] = undefined
isHex list = all (`elem` "0123456789abcdefABCDEF") list
