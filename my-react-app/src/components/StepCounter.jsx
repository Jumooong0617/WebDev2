import { useState } from "react";

export default function StepCounter() {
  const [count, setCount] = useState(0);
  const [step, setStep] = useState(1);

  return (
    <div>
      <h2>Step Counter</h2>

      <p>Count: {count}</p>

      <input
        type="number"
        value={step}
        onChange={(e) => setStep(Number(e.target.value))}
        placeholder="Enter step value"
      />

      <button onClick={() => setCount(count + step)}>Add</button>
    </div>
  );
}
