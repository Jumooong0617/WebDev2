import "./style.css";
import ProfileList from "./components/ProfileList.jsx";
import StepCounter from "./components/StepCounter.jsx";
import ProductInfo from "./components/ProductInfo.jsx";

export default function App() {
  return (
    <div className="app-container">
      <h1>React Crash Course Activity</h1>

      <div className="profile-list">
        <ProfileList />
      </div>

      <div className="step-counter">
        <StepCounter />
      </div>

      <ProductInfo
        name="Gaming Laptop"
        price={45000}
        details="16GB RAM • RTX 4060 • 512GB SSD"
        className="product-card"
      />

      <ProductInfo
        name="Wireless Headset"
        price={2500}
        details="Noise-cancelling • 40hr battery life"
        className="product-card"
      />
    </div>
  );
}
