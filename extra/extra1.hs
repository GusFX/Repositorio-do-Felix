filtraTupla :: [(Int,Int)] -> [Int]
filtraTupla list = filter (even) (map (fst) list) 
