import { useState } from "react";
import Sidebar from "./Sidebar";
import Header from "./Header";
import Footer from "./Footer";

export default function Layout({ children }) {
  const [isOpen, setIsOpen] = useState(true);

  return (
    <div className="layout">
      <Sidebar isOpen={isOpen} />

      <div className="main-content">
        <Header toggleSidebar={() => setIsOpen(!isOpen)} />

        <div className="page-content">
          {children}
        </div>

        <Footer />
      </div>
    </div>
  );
}
