SELECT  province.COD_P, TimeDim.COD_T, CategoryDim.COD_C, stagingTable.netIncome, stagingTable.numberRentedItems
FROM stagingTable,Province,TimeDim,CategoryDim
where stagingTable.province = Province.province
  and stagingTable.month = TimeDim.month
  and stagingTable.year = TimeDim.year
  and stagingTable.category=categoryDim.category;
