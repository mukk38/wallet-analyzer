import { BrowserRouter, Routes, Route } from "react-router-dom";
import AddressForm from "./components/AddressForm";
import Dashboard from "./pages/Dashboard";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AddressForm />} />
        <Route path="/dashboard/:address" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
