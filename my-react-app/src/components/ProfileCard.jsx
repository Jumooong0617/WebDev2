export default function ProfileCard({ name, age, role }) {
  return (
    <div className="profile-card">
      <p><strong>Name:</strong> {name}</p>
      <p><strong>Age:</strong> {age}</p>
      <p><strong>Role:</strong> {role}</p>
    </div>
  );
}
