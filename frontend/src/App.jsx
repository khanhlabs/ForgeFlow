import LoginPage from "./pages/login/LoginPage.jsx";
import RegisterPage from "./pages/register/RegisterPage.jsx";
import {Route, Routes} from "react-router-dom";


function App() {
  return (
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage/>}/>
      </Routes>
  );
}

export default App
