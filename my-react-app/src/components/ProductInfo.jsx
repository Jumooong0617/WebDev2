import { useState } from "react";

export default function ProductInfo({ name, price, details, className }) {
  const [showDetails, setShowDetails] = useState(false);

  return (
    <div className={className}>
      <h2>{name}</h2>
      <p>Price: ₱{price}</p>

      <button onClick={() => setShowDetails(!showDetails)}>
        {showDetails ? "Hide Details" : "Show Details"}
      </button>

      <div className={`details ${showDetails ? "show" : ""}`}>
        {details}
      </div>
    </div>
  );
}
