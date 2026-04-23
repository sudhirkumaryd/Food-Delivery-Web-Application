import React, { useEffect, useState } from 'react'
import "./List.css"
import axios from 'axios';
import { toast } from 'react-toastify';
const List = () => {
  const url = "http://localhost:8080"
  const [list, setList] = useState([])
  const fetchList = async () => {
    try {
      const response = await axios.get(`${url}/items/all`);
      setList(response.data);   // direct set
    } catch (error) {
      toast.error("Error fetching data");
    }
  };
  const removeFood = async (id) => {
    try {
      await axios.delete(`${url}/items/delete/${id}`);
      toast.success("Item deleted");
      fetchList();
    } catch (error) {
      toast.error("Error deleting item");
    }
  };
  useEffect(() => {
    fetchList();

  }, [])

  return (
    <div className='list add flex-col'>
      <p>All Foods List</p>
      <div className="list-table">
        <div className="list-table-format title">
          <b>Image</b>
          <b>Name</b>
          <b>Category</b>
          <b>Price</b>
          <b>Action</b>
        </div>
        {list.map((item, index) => {
          return (
            <div key={index} className='list-table-format'>
              <img src={`${url}/images/${item.imageName}`} alt="" />
              <p>{item.name}</p>
              <p>{item.category}</p>
              <p>${item.price}</p>
              <p onClick={() => removeFood(item.id)} className='cursor'>X</p>
            </div>)
        })
        }

      </div>
    </div>

  )


}
export default List