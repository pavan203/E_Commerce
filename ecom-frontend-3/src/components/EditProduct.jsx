import React, { useState, useEffect, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AppContext from "../Context/Context";
import axios from "axios";

const EditProduct = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { data, refreshData } = useContext(AppContext);
  const [product, setProduct] = useState(null);

  useEffect(() => {
    const selected = data.find((p) => p.id === parseInt(id));
    if (selected) setProduct(selected);
  }, [id, data]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/product/${id}`, product);
      await refreshData();
      navigate("/");
    } catch (error) {
      console.error("Failed to update product:", error);
    }
  };

  if (!product) return <p>Loading product details...</p>;

  return (
    <div className="container mt-5">
      <h2>Edit Product</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group mb-3">
          <label>Product Name</label>
          <input
            type="text"
            name="name"
            className="form-control"
            value={product.name}
            onChange={handleChange}
          />
        </div>
        <div className="form-group mb-3">
          <label>Brand</label>
          <input
            type="text"
            name="brand"
            className="form-control"
            value={product.brand}
            onChange={handleChange}
          />
        </div>
        <div className="form-group mb-3">
          <label>Category</label>
          <input
            type="text"
            name="category"
            className="form-control"
            value={product.category}
            onChange={handleChange}
          />
        </div>
        <div className="form-group mb-3">
          <label>Price</label>
          <input
            type="number"
            name="price"
            className="form-control"
            value={product.price}
            onChange={handleChange}
          />
        </div>
        <div className="form-group mb-4">
          <label>Availability</label>
          <select
            name="productAvailable"
            className="form-control"
            value={product.productAvailable ? "true" : "false"}
            onChange={(e) =>
              setProduct((prev) => ({
                ...prev,
                productAvailable: e.target.value === "true",
              }))
            }
          >
            <option value="true">Available</option>
            <option value="false">Out of Stock</option>
          </select>
        </div>
        <button type="submit" className="btn btn-success me-2">
          Save Changes
        </button>
        <button
          type="button"
          className="btn btn-secondary"
          onClick={() => navigate("/")}
        >
          Cancel
        </button>
      </form>
    </div>
  );
};

export default EditProduct;
