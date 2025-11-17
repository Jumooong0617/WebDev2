import ProfileList from "src\components\ProductInfo.jsx";
import StepCounter from "src\components\StepCounter.jsx";
import ProductInfo from "src\components\ProductInfo.jsx";

export default function App() {
  return (
    <div>
      <h1>React Crash Course Activity</h1>

      <ProfileList />

      <StepCounter />

      <ProductInfo
        name="Gaming Laptop"
        price={45000}
        details="16GB RAM • RTX 4060 • 512GB SSD"
      />

      <ProductInfo
        name="Wireless Headset"
        price={2500}
        details="Noise-cancelling • 40hr battery life"
      />
    </div>
  );
}
