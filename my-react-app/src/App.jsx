import { useState } from "react";
import Sidebar from "./components/Sidebar";
import Footer from "./components/Footer";
import Header from "./components/Header";

const App = () => {
  const [sidebarOpen, setSidebarOpen] = useState(true);

  function toggleSidebar() {
    setSidebarOpen(!sidebarOpen);
  }

  return (
    <>
      <Sidebar />
      <Header onSidebarToggle={toggleSidebar} />

      <main>
        <p>{sidebarOpen && "Sidebar open"}</p>
      </main>

      <Footer />
    </>
  );
};

export default App;
