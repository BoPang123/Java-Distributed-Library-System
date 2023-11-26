/*
 *
 */


export function  bigger( numA, numB){
    return numA - numB >0 ? numA: numB;
}

export function smaller(numA, numB) {
  return numA - numB <0 ? numA: numB;
}

export default function subList(list , from, to){
  let result = []
  let i =0 ;
  for(i= bigger(0, from);  i<smaller(to, list.length); i++){
    // let item = list[i]
    // console.log('list[',i,"':", item)
    result.push(list[i])
  }
  // console.log('from', from, 'to', to, 'list.length', list.length, 'sublist', result)
  return result
}
