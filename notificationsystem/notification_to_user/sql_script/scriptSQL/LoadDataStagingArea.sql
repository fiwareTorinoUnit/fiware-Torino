select month(date) as month, 
       year(date) as year, 
       'Torino' as Province,
       Category,
       count(*) as numberOfRentedItems,
       sum(cost) as netIncome
  from rental,item, itemType
  where rental.itemkey=item.itemkey
    and item.typekey=itemtype.typekey
  group by month(date), year(date), itemtype.category;