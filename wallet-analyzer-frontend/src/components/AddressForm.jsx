import { useState } from "react";
import { useNavigate } from "react-router-dom";

const AddressForm = () => {
  const [address, setAddress] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (address.trim().length === 42) {
      navigate(`/dashboard/${address}`);
    } else {
      alert("Geçerli bir Ethereum adresi girin.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <form
        onSubmit={handleSubmit}
        className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md"
      >
        <h1 className="text-2xl font-bold mb-4 text-center">Wallet Analyzer</h1>
        <input
          type="text"
          placeholder="Ethereum Cüzdan Adresi"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <button
          type="submit"
          className="mt-4 w-full bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition"
        >
          Analiz Et
        </button>
      </form>
    </div>
  );
};

export default AddressForm;
